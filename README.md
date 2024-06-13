# WikiPages Scala Project

## Overview
WikiPages est une application Scala conçue pour récupérer et afficher des statistiques sur les pages Wikipedia en fonction d'un mot-clé fourni par l'utilisateur. Elle utilise l'API Wikipedia pour rechercher des pages, puis analyse et présente des informations telles que les titres des pages et le nombre de mots. Le projet démontre l'utilisation des requêtes HTTP et de l'analyse JSON en Scala, en utilisant des bibliothèques telles que `scalaj.http` pour la communication HTTP et `play-json` pour le traitement JSON.

## Features
- **Mot-clé de recherche**: L'utilisateur peut saisir un mot-clé pour rechercher des pages Wikipedia.
- **Affichage des statistiques**: Affiche les titres des pages trouvées et le nombre de mots dans chaque page.
- **Gestion des erreurs**: Gère les erreurs telles que les pages non trouvées ou les erreurs de communication avec l'API.

## Technologies
- **Scala**: Le projet est écrit en Scala, un langage de programmation polyvalent.
- **scalaj.http**: Librairie utilisée pour effectuer des requêtes HTTP vers l'API Wikipedia.
- **play-json**: Librairie utilisée pour analyser les réponses JSON de l'API Wikipedia.

## Lancer l'application

### Lien

Le projet est hébergé sur GitHub à l'adresse suivante : [WikiPages](https://github.com/Bertrand2808/wikipages)
### Prerequis
- Scala 2.13.x
- sbt (Scala Build Tool)

### Running the Application
1. Cloner le projet sur votre machine locale.
2. Naviguer vers le répertoire du projet:
    ```powershell
    cd WikiPages
    ```
3. Lancer l'application en utilisant la commande suivante:
(Par exemple pour rechercher le mot OpenAI avec une limite de pages de 5)
    ```powershell
    sbt run -l=5 OpenAI
    ```

Le résultat de la recherche sera affiché dans la console :
```powershell
[info] running Main -l=10 OpenAI
Les dependances sont bien ajoutees et importees!
Liste des pages trouvees : 10 au total
- OpenAI / 15498 words
- OpenAI Codex / 1306 words
- OpenAI Five / 2254 words
- Sora (text-to-video model) / 1239 words
- ChatGPT / 16057 words
- Removal of Sam Altman from OpenAI / 5812 words
- GPT-4 / 5834 words
- Generative artificial intelligence / 10085 words
- Sam Altman / 4721 words
- Gemini (chatbot) / 8577 words
Nombre total de mots : 71383
Nombre moyen de mots par page : 7138.3
```

### Tests

Pour lancer les tests, exécutez la commande suivante:

  ```powershell
  sbt test
  ```

Le résultat des tests sera affiché dans la console:
```powershell
[info] MainSpec:
[info] A simple test
[info] - should pass when everything is OK
[info] formatUrl
[info] - should format the URL correctly
[info] parseJson
[info] - should extract titles from JSON correctly
[info] totalWords
[info] - should return 0 for an empty list
[info] - should return the correct total word count for a non-empty list
[info] parseArguments
[info] - should return None for non-parsable arguments
[info] - should parse arguments with a keyword correctly
[info] - should parse arguments with a keyword and limit correctly
[info] getPages
[info] - should return the correct response body for a successful request
[info] Run completed in 346 milliseconds.
[info] Total number of tests run: 9
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 9, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 1 s, completed 13 juin 2024, 09:15:32
```

## Auteur

Partie A `Code Source` réalisée par `RENAUDIN Bertrand`.

Partie B `Tests Unitaires` réalisée par `PENOT-PERBET Aurélien`


