package fr.uphf.bienImmo.services;
import fr.uphf.bienImmo.resources.BienImmoDTO;
import fr.uphf.bienImmo.resources.*;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


import java.util.List;
import java.util.Optional;

@Service
public class BienImmoApiService {

    @Autowired
    private WebClient.Builder webClient;

    @Autowired
    private BienImmoRepository bienImmoRepository;


    public BienImmoApiService(BienImmoRepository bienImmoRepository) {
        this.bienImmoRepository = bienImmoRepository;
    }


    //RabbitMQ pour la communication entre les microservices
    @Autowired
    private RabbitTemplate rabbitTemplate;


    // fonction mise en commentaire simulant l'envoi d'un bienImmo à RabbitMQ depuis le microservice BienImmo (Producer)
    // vers les microservice Locataire et Reservation (Consumers)
    /*
    @Scheduled(fixedDelay = 5000)
    public void sendBienImmo() {
        BienImmoDTO bienImmo = BienImmoDTO.builder()
                .adresse("adresse")
                .type("type")
                .surface(100)
                .loyer(1000)
                .nbPieces(5)
                .locataire(BienImmoDTO.LocataireDTO.builder().idLocataire(1L).build())
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, bienImmo);
        System.out.println("Send msg = " + bienImmo);
    }
*/


    //********* services internes au microservice bienImmo *********

    //récupérer tous les biensImmo
    public List<BienImmo> listerBiensImmo() {
        return bienImmoRepository.findAll();
    }

    //récupérer un bienImmo par son id
    public BienImmo recupererBienImmoById(Long id) {
        Optional<BienImmo> bienImmo = bienImmoRepository.findById(id);
        return bienImmo.orElseThrow(null);
    }

    //ajouter un bienImmo
    public CreationBienImmoResponseODT ajouterBienImmo(CreationBienImmoRequestODT creationBienImmoRequestODT) {
        BienImmo bienImmoAAjouter = BienImmo.builder()
                .adresse(creationBienImmoRequestODT.getAdresse())
                .type(creationBienImmoRequestODT.getType())
                .surface(creationBienImmoRequestODT.getSurface())
                .loyer(creationBienImmoRequestODT.getLoyer())
                .nbPieces(creationBienImmoRequestODT.getNbPieces())
                .idLocataire(creationBienImmoRequestODT.getLocataire().getIdLocataire())
                .build();
        BienImmo bienImmoSauvegarder = bienImmoRepository.save(bienImmoAAjouter);
        return CreationBienImmoResponseODT.builder()
                .idBienImmo(bienImmoSauvegarder.getIdBienImmo())
                .adresse(bienImmoSauvegarder.getAdresse())
                .type(bienImmoSauvegarder.getType())
                .surface(bienImmoSauvegarder.getSurface())
                .loyer(bienImmoSauvegarder.getLoyer())
                .nbPieces(bienImmoSauvegarder.getNbPieces())
                .locataire(bienImmoSauvegarder.getIdLocataire() != null ? BienImmoDTO.LocataireDTO.builder().idLocataire(bienImmoSauvegarder.getIdLocataire()).build() : null)
                .build();
    }

    //service pour modifier un bienImmo
    public CreationBienImmoResponseODT modifierBienImmo(Long idBienImmo, CreationBienImmoResponseODT creationBienImmoResponseODT) {
        Optional<BienImmo> bienImmoOption = bienImmoRepository.findById(idBienImmo);
        if (bienImmoOption.isPresent()) {
            BienImmo bienImmo = bienImmoOption.get();
            bienImmo.setIdBienImmo(creationBienImmoResponseODT.getIdBienImmo());
            bienImmo.setAdresse(creationBienImmoResponseODT.getAdresse());
            bienImmo.setType(creationBienImmoResponseODT.getType());
            bienImmo.setSurface(creationBienImmoResponseODT.getSurface());
            bienImmo.setLoyer(creationBienImmoResponseODT.getLoyer());
            bienImmo.setNbPieces(creationBienImmoResponseODT.getNbPieces());
            bienImmo.setIdLocataire(creationBienImmoResponseODT.getLocataire().getIdLocataire());

            BienImmo bienImmoModifier = bienImmoRepository.save(bienImmo);
            //appel de la fonction pour envoyer un bienImmo à RabbitMQ
            sendBienImmo(bienImmoModifier);
            System.out.println("Remarque");
            return CreationBienImmoResponseODT.builder()
                    .idBienImmo(bienImmoModifier.getIdBienImmo())
                    .adresse(bienImmoModifier.getAdresse())
                    .type(bienImmoModifier.getType())
                    .surface(bienImmoModifier.getSurface())
                    .loyer(bienImmoModifier.getLoyer())
                    .nbPieces(bienImmoModifier.getNbPieces())
                    .locataire(bienImmoModifier.getIdLocataire() != null ? BienImmoDTO.LocataireDTO.builder().idLocataire(bienImmoModifier.getIdLocataire()).build() : null)
                    .build();
        } else {
            throw new RuntimeException("Le bienImmo n'existe pas");
        }

    }

    //fonction pour envoyer un bienImmo à RabbitMQ lors de sa modification
    public void sendBienImmo(BienImmo bienImmo) {
        BienImmoDTO bienImmoDTO = BienImmoDTO.builder()
                .idBienImmo(bienImmo.getIdBienImmo())
                .adresse(bienImmo.getAdresse())
                .type(bienImmo.getType())
                .surface(bienImmo.getSurface())
                .loyer(bienImmo.getLoyer())
                .nbPieces(bienImmo.getNbPieces())
                .locataire(bienImmo.getIdLocataire() != null ? BienImmoDTO.LocataireDTO.builder().idLocataire(bienImmo.getIdLocataire()).build() : null)
                .build();
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, bienImmoDTO);
        System.out.println("Send msg = " + bienImmoDTO);
    }

    //service pour supprimer un bienImmo


    //********* services pour communiquer avec le microservice Locataire *********
    //ajouter un locataire à un bienImmo
    public CreationBienImmoResponseODT addLocataireToBienImmo(Long idBienImmo, Long idLocataire) {
        return webClient.baseUrl("http://bienImmo/")
                .build()
                .put()
                .uri("/bienImmo/" + idBienImmo + "/locataire/" + idLocataire)
                .retrieve()
                .bodyToMono(CreationBienImmoResponseODT.class)
                .block();
    }

    // méthode de service pour lister les biensImmo d'un locataire
    public List<BienImmoDTO> listerBiensImmoParLocataire(Long idLocataire) {
        List<BienImmo> biens = bienImmoRepository.findByIdLocataire(idLocataire);
        return biens.stream().map(bienImmo -> BienImmoDTO.builder()
                .idBienImmo(bienImmo.getIdBienImmo())
                .adresse(bienImmo.getAdresse())
                .type(bienImmo.getType())
                .surface(bienImmo.getSurface())
                .loyer(bienImmo.getLoyer())
                .nbPieces(bienImmo.getNbPieces())
                .locataire(bienImmo.getIdLocataire() != null ? BienImmoDTO.LocataireDTO.builder().idLocataire(bienImmo.getIdLocataire()).build() : null)
                .build()).toList();
    }
    public Flux<ReservationResponse> getReservationsByBienImmoId(Long idBienImmo) {
        return webClient.build()
                .get()
                .uri("http://localhost:8083/reservation/reservation/reservation/" + idBienImmo)
                .retrieve()
                .bodyToFlux(ReservationResponse.class);
    }
}
