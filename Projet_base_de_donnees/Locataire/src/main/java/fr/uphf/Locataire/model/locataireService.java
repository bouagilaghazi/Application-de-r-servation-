package fr.uphf.Locataire.model;

import fr.uphf.Locataire.DTO.BienDTO;
import fr.uphf.Locataire.DTO.LocataireResponse;
import fr.uphf.Locataire.config.RabbitMQConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.util.List;
import java.util.Optional;

@Service
public class locataireService {

    @Autowired
    private fr.uphf.Locataire.model.locataireRepository locataireRepository;

    @Autowired
    private WebClient.Builder webClient;

    public List<locataire> getAllLocataires() {
        return locataireRepository.findAll();
    }

    public locataire getLocataireById(Long id) {
        return locataireRepository.findById(id).orElse(null);
    }

    public locataire addLocataire(locataire locataire) {
        return locataireRepository.save(locataire);
    }

    //Méthode PUT pour modofier un locataire
    public LocataireResponse updateLocataire(Long id, LocataireResponse locatairResponse){
        Optional<locataire> locataireOption = locataireRepository.findById(id);
        if(locataireOption.isPresent()){
            locataire locataire = locataireOption.get();
            locataire.setId(locatairResponse.getId());
            locataire.setNom(locatairResponse.getNom());
            locataire.setPrenom(locatairResponse.getPrenom());
            locataire.setAdresse(locatairResponse.getAdresse());
            locataire.setNumTel(locatairResponse.getNumTel());
            locataire locataireModifier = locataireRepository.save(locataire);
            return LocataireResponse.builder()
                    .id(locataireModifier.getId())
                    .nom(locataireModifier.getNom())
                    .prenom(locataireModifier.getPrenom())
                    .adresse(locataireModifier.getAdresse())
                    .numTel(locataireModifier.getNumTel())
                    .build();
        }else {
            throw new RuntimeException("Le locataire n'existe pas");
        }

    }

    public void deleteLocataire(Long id) { locataireRepository.deleteById(id);
    }

    public List<BienDTO> listerBiensLocataire(Long idLocataire) {
        return webClient
                .build()
                .get()
                .uri("http://localhost:8083/reservation/reservation/reservation/"+idLocataire)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BienDTO.class)
                .collectList()
                .block();
    }


    //Configuration en tant que Consumer RabbitMQ pour la communication entre les microservices
    @RabbitListener(queues = "#{T(fr.uphf.Locataire.config.RabbitMQConfig).QUEUE}")
    public void receiveBienImmo(BienDTO bienImmo) {
        System.out.println("Received msg = " + bienImmo);

    }


}