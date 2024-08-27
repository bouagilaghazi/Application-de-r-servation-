## Introduction
Ce projet est une application basée sur des microservices conçue pour gérer les réservations, les locataires et fournir une passerelle pour accéder à ces services. Elle utilise le serveur Eureka pour la découverte des services.

## Vue d'ensemble de l'architecture
L'application se compose des microservices suivants :

1. **Serveur Eureka (ServeurRegistresApplication)**
   - Description : Il s'agit du registre de services où tous les autres microservices s'enregistrent.
   - Responsabilités : Découverte et enregistrement des services.

2. **Service de Réservation (ReservationApplication)**
   - Description : Gère les réservations

3. **Service de Locataire (LocataireApplication)**
   - Description : Gère les locataires.

4. **Service de Passerelle (GatewayApplication)**
   - Description : Sert de passerelle pour accéder aux autres microservices.
     
5. **Service BienImmobilier**
   - Description : Gérer les bien immobilier .
  

Chaque microservice communique avec les autres via des API REST et utilise le serveur Eureka pour la découverte des services.

## Configuration et Installation

### Prérequis
- Java 8 ou supérieur
- Maven
- [Autres prérequis, par exemple, Docker, Node.js, etc.]

### Étapes d'installation
1. Cloner le dépôt :
   ```sh
   git clone https://github.com/votre-repo/nom-du-projet.git
   cd nom-du-projet

### Exécution de l'application
1 .Démarrer le Serveur Eureka
2. Naviguer dans le répertoire du Serveur Eureka :
cd ../reservation
mvn spring-boot:run
cd ../locataire
mvn spring-boot:run
cd ../gateway
mvn spring-boot:run
Lancer le Serveur Eureka :
Démarrer les autres microservices
Naviguer dans le répertoire de chaque microservice et lancer le service :
### Accéder à l'application
Tableau de bord du Serveur Eureka : http://localhost:8761
