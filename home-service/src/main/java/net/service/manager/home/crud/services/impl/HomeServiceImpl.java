package net.service.manager.home.crud.services.impl;

import net.service.manager.home.crud.dto.reponse.HomeReponse;
import net.service.manager.home.crud.dto.reponse.LiteSectionReponse;
import net.service.manager.home.crud.dto.request.HomeRequest;
import net.service.manager.home.crud.entities.Home;
import net.service.manager.home.crud.enums.SectionType;
import net.service.manager.home.crud.mapper.HomeMapper;
import net.service.manager.home.crud.repositories.HomeRepository;
import net.service.manager.home.crud.repositories.HomeSearchRepository;
import net.service.manager.home.crud.services.HomeService;
import net.service.manager.home.generic.service.impl.ServiceGenericImpl;
import net.service.manager.home.rest.client.*;
import net.service.manager.home.rest.model.*;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HomeServiceImpl extends ServiceGenericImpl<HomeRequest, HomeReponse, Home> implements HomeService {
    private final HomeRepository repository;
    private final HomeMapper mapper;
    private final AboutService aboutService;
    private final CareerService careerService;
    private final ContactService contactService;
    private final OfferService offerService;
    private final PartnerService partnerService;

    public HomeServiceImpl(JpaEntityInformation<Home, Long> entityInformation, HomeRepository repository, HomeSearchRepository searchRepository, HomeMapper mapper, AboutService aboutService, CareerService careerService, ContactService contactService, OfferService offerService, PartnerService partnerService) {
        super(entityInformation, repository, searchRepository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.aboutService = aboutService;
        this.careerService = careerService;
        this.contactService = contactService;
        this.offerService = offerService;
        this.partnerService = partnerService;
    }

    @Override
    public HomeReponse getCurrentHomePage() {
        Home home = repository.findCurrentPage();
        HomeReponse homeReponse = mapper.toDto(home);
        homeReponse.setSections(new ArrayList<>());
        homeReponse.getSections().forEach(section -> {
            switch (section.getType()) {
                case ABOUT -> section.setAbouts(getAllVisibleAbouts());
                case CAREER -> section.setCareers(getAllVisibleCareers());
                case CONTACT -> section.setContacts(getAllVisibleContacts());
                case OFFER -> section.setOffers(getAllVisibleOffers());
                case PARTNER -> section.setPartners(getAllVisiblePartners());
            }
        });
        return homeReponse;
    }

    @Override
    public LiteSectionReponse detailsSectionPage(Long sectionId, SectionType type) {
        switch (type) {
            case ABOUT:
                return getVisibleAbouts(sectionId, type);
            case CAREER:
                return getVisibleCareers(sectionId, type);
            case CONTACT:
                return getVisibleContacts(sectionId, type);
            case OFFER:
                return getVisibleOffers(sectionId, type);
            case PARTNER:
                return getVisiblePartners(sectionId, type);
            default:
                return new LiteSectionReponse(type);
        }
    }

    private LiteSectionReponse getVisiblePartners(Long sectionId, SectionType type) {
        PartnerReponse partner = partnerService.findPartner(sectionId);
        LiteSectionReponse lite = new LiteSectionReponse();
        lite.setType(type);
        lite.setPartner(partner);
        return lite;
    }

    private LiteSectionReponse getVisibleOffers(Long sectionId, SectionType type) {
        FullOfferReponse offer = offerService.findOffer(sectionId);
        LiteSectionReponse lite = new LiteSectionReponse();
        lite.setType(type);
        lite.setOffer(offer);
        return lite;
    }

    private LiteSectionReponse getVisibleCareers(Long sectionId, SectionType type) {
        FullCareerReponse career = careerService.findCareer(sectionId);
        LiteSectionReponse lite = new LiteSectionReponse();
        lite.setType(type);
        lite.setCareer(career);
        return lite;
    }

    private LiteSectionReponse getVisibleContacts(Long sectionId, SectionType type) {
        ContactReponse contact = contactService.findContact(sectionId);
        LiteSectionReponse lite = new LiteSectionReponse();
        lite.setType(type);
        lite.setContact(contact);
        return lite;
    }

    private LiteSectionReponse getVisibleAbouts(Long sectionId, SectionType type) {
        AboutReponse about = aboutService.findAbout(sectionId);
        LiteSectionReponse lite = new LiteSectionReponse();
        lite.setType(type);
        lite.setAbout(about);
        return lite;
    }

    private List<PartnerReponse> getAllVisiblePartners() {
        return partnerService.findAllPartner().getContent().stream().toList();
    }

    private List<FullOfferReponse> getAllVisibleOffers() {
        return offerService.findAllOffers().getContent().stream().toList();
    }

    private List<FullCareerReponse> getAllVisibleCareers() {
        return careerService.findAllCareer().getContent().stream().toList();
    }

    private List<ContactReponse> getAllVisibleContacts() {
        return contactService.findAllContact().getContent().stream().toList();
    }

    private List<AboutReponse> getAllVisibleAbouts() {
        return aboutService.findAllAbout().getContent().stream().toList();
    }
}
