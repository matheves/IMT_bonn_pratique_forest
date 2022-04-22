# TP Noté Bonne pratique de code

## Réalisation de l'API REST

### Persistance des données

Implémentation des interfaces ForestRepository et TreeRepository.

Ajout de méthodes dans TreeRepository

- findById
- UpdateById
- delete

Copie des même méthodes dans ForestRepository

### Développement du service

Création des interfaces TreeService et ForestService et implémentation

Méthodes implémenter :

- get
- list
- save
- update
- delete

Tests implémentés :

- shouldGet Tree/Forest
- shouldSaveAValid Tree/Forest
- shouldNotSaveAnInvalid Tree/Forest
- shouldUpdateA Tree/Forest
- shouldDeleteA Tree/Forest

### Création de l'API REST

Edition du fichier forest.yml dans forest-api
Permettant de réaliser les actions suivantes sur les trees et forests:

- GET by id et all
- POST
- PUT
- DELETE

### Déploiement de l'API REST via les controllers

Implémentation des controllers sur Trees et Forest afin de:

- Get all et by id
- update
- insert
- delete

Implémentation de test sur les controllers

- shouldGetById
- shouldGetAll

## Lancer l'application

Pour lancer l'application il faut Gradle
Dans Intellij ouvrez la fenêtre de Gradle puis :

- forest/forest-app/forest-web/application/bootRun

