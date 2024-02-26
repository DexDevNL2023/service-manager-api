package net.service.manager.career.generic.email;

import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.service.manager.career.generic.config.MessageService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
@Transactional
public class MailService {

    private final JavaMailSender mailSender;

    private final Configuration freemarkerConfiguration;

    private final MessageService messageService;

    public MailService(JavaMailSender mailSender, Configuration freemarkerConfiguration, MessageService messageService) {
        this.mailSender = mailSender;
        this.freemarkerConfiguration = freemarkerConfiguration;
        this.messageService = messageService;
    }

    private String geFreeMarkerTemplateContent(Map<String, Object> model) {
        StringBuilder content = new StringBuilder();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("mail/confirmationpage.ftl"), model));
            return content.toString();
        } catch (Exception e) {
            System.out.println("Exception occured while processing fmtemplate:" + e.getMessage());
        }
        return "";
    }

    public void sendHtmlMail(String from, String to, String subject, String body) throws MessagingException {
        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
        helper.setFrom(from);
        if (to.contains(",")) {
            helper.setTo(to.split(","));
        } else {
            helper.setTo(to);
        }
        helper.setSubject(subject);
        helper.setText(body, true);
        mailSender.send(mail);
        System.out.println("Sent mail: " + subject);
    }
}
