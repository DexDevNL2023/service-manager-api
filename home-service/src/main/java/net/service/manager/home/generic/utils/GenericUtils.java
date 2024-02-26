package net.service.manager.home.generic.utils;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.service.manager.home.generic.exceptions.RessourceNotFoundException;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

@Component
@Slf4j
public class GenericUtils {

    private static final String URL_REGEX =
            "^(www.)" + "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])" +
                    "([).!';/?:,][[:blank:]])?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    public static String upperCaseTrim(String str) {
        return str == null ? null : str.toUpperCase().trim();
    }

    public static String lowerCaseTrim(String str) {
        return str == null ? null : str.toLowerCase().trim();
    }

    public static Date calculExpiredDate(Date saved_date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(saved_date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    public static String RandGeneratedKey(String str, int l) {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        StringBuilder sb = new StringBuilder(l);
        for (int i = 0; i < l; i++) {
            int index = (int) (AlphaNumericStr.length() * Math.random());
            sb.append(AlphaNumericStr.charAt(index));
        }
        String key = str + sb;

        return key;
    }

    public static String GenerateToken(int token) {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder sb = new StringBuilder(token);
        for (int i = 0; i < token; i++) {
            int index = (int) (AlphaNumericStr.length() * Math.random());
            sb.append(AlphaNumericStr.charAt(index));
        }
        return sb.toString();
    }

    public static Instant currentInstant() {
        return Instant.now().truncatedTo(ChronoUnit.MICROS);
    }

    public static Instant currentDate() {
        return Instant.from(currentInstant());
    }

    public static String generatedPassWord() {
        String AlphaNumericStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789";

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = (int) (AlphaNumericStr.length() * Math.random());
            sb.append(AlphaNumericStr.charAt(index));
        }
        String pass = sb.toString();
        return pass;
    }

    public static String UniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String GenerateNumEnrg(String prefixe) {
        String randomCode = RandomString.make(64);
        String number = String.format("%04d", randomCode.charAt(10000));
        return prefixe + "-" + number;
    }

    public static String generateTokenNumber() {
        String randomCode = RandomString.make(6);
        return String.format("%06d", randomCode.charAt(10000));
    }

    public static String GenerateMatricule(String prefixe) {
        DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits

        Random random = new Random();
        String number = String.format("%05d", random.nextInt(100000));
        return prefixe + "-" + number;
    }

    public static boolean urlValidator(String url) {
        try {
            new URI(url).parseServerAuthority();
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    public static final String getSiteURL(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");
    }

    public static String getStringFromRequest(HttpServletRequest request, String link) {
        try {
            String siteUrl = request.getRequestURL().toString();
            URL url = new URL(siteUrl + link);
            log.info(url.toString());
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder html = new StringBuilder();
            String val;
            while ((val = in.readLine()) != null) {
                html.append(val);
            }
            String result = html.toString();
            in.close();
            return result;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return null;
        }
    }

    public static String getServerAbsoluteUrl() {
        try {
            URL url = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host(AppConstants.SERVEUR_ADRESS)
                    .port(AppConstants.SERVEUR_PORT)
                    .build()
                    .toUri()
                    .toURL();
            return url.getPath();
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return null;
        }
    }

    public static final String getBasicAuthenticationHeader(String uuid, String key) {
        String valueToEncode = uuid + ":" + key;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public static int calculAge(Date dateNaissance) {
        Calendar current = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();
        birthday.setTime(dateNaissance);
        int yearDiff = current.get((Calendar.YEAR) - birthday.get(Calendar.YEAR));
        if (birthday.after(current)) {
            yearDiff = yearDiff - 1;
        }
        return yearDiff;
    }

    public static void validatePageNumberAndSize(final Integer page, final Integer size) {
        if (page < 0) {
            throw new RessourceNotFoundException("Le numéro de page ne peut pas être inférieur à zéro.");
        }

        if (size > Integer.getInteger(AppConstants.DEFAULT_PAGE_SIZE)) {
            throw new RessourceNotFoundException("La taille de la page ne doit pas être supérieure à " + AppConstants.DEFAULT_PAGE_SIZE);
        }
    }

    public static Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public static Path FileStorageService(Environment env) {
        Path fileStorageLocation = Paths.get(env.getProperty("app.file.upload-dir", "data/uploads/files"))
                .toAbsolutePath().normalize();

        try {
            return Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Impossible de créer le répertoire dans lequel les fichiers téléchargés seront stockés.", ex);
        }
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        String[] fileNameParts = fileName.split("\\.");

        return fileNameParts[fileNameParts.length - 1];
    }

    public static String storeFile(MultipartFile file, Path fileStorageLocation) {
        // Normalize file name
        String fileName = new Date().getTime() + "-file." + getFileExtension(file.getOriginalFilename());

        try {
            // Check if the filename contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException(
                        "Désolé! Le nom du fichier contient une séquence de chemin non valide" + fileName);
            }

            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de stocker le fichier " + fileName + ". Veuillez réessayer!", ex);
        }
    }

    public static String verifieFormatLangue(String langKey) {
        return switch (langKey) {
            case "En" -> "En";
            case "Esp" -> "Esp";
            default -> "Fr"; // default language
        };
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    // Fonction pour convertir le camel casse en chaîne snake casse
    public static String camelToSnake(String str) {
        // Chaîne vide
        String result = "";

        // Ajoute le premier caractère (en minuscule) vers la chaîne de résultat
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);

        // Parcourt la chaîne depuis est l'index jusqu'au dernier index
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Vérifie si le caractère est en majuscule puis ajoutez '_' et ce caractère
            // (en minuscules) vers la chaîne de résultat
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result = result + Character.toLowerCase(ch);
            }

            // Si le caractère est en minuscule alors
            // ajoute un tel caractère dans la chaîne de résultat
            else {
                result = result + ch;
            }
        }

        // renvoie le résultat
        return result;
    }

    public static String camelOrSnakeToKey(String str) {
        // Expression régulière
        String regex = "([a-z])([A-Z]+)" + ("_([a-z])");

        // Chaîne de remplacement
        String replacement = "$1-$2";

        // Remplace l'expression régulière donnée avec chaîne de remplacement
        // et convertissez-le en minuscules.
        str = str.replaceAll(regex, replacement).toUpperCase();

        // renvoie le résultat
        return str;
    }

    public static String camelOrSnakeToLabel(String str) {
        // Expression régulière
        String regex = "([a-z])([A-Z]+)" + ("_([a-z])");

        // Chaîne de remplacement
        String replacement = "$1 $2";

        // Remplace l'expression régulière donnée avec chaîne de remplacement
        // et convertissez-le en minuscules.
        str = str.replaceAll(regex, replacement).toLowerCase();

        // renvoie le résultat
        return str;
    }
}
