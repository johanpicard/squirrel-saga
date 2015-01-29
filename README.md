squirrel-saga
=============

# Organisation du dossier

Les sources java du projet se situent dans le dossier *app/src/main/java/com/squirrelsaga*

Elles sont organisées selon un modèle MVC standard.

**Le package "controleur"** contient uniquement le contrôleur, qui a pour fonction d'initialiser l'application (arbres et quêtes), ainsi que de contenir l'objet Ecureuil utilisé par toutes les parties de l'application.
Ce contrôleur est entièrement statique.

**Le package "modele"** contient toutes les classes du modèle de l'application, à savoir l'écureuil, les arbres et les quêtes. Ce sont les objets élémentaires utilisés par les différentes fonctionnalités.

**Le package "vue"** contient une classe pour chaque Activity, soit pour chaque fenêtre de l'application. Ces classes contiennent la logique d'affichage des différents composants.
Chacune possède une méthode onCreate() appelée, comme son nom l'indique, à la création de la fenêtre, qui initialise tous les éléments à afficher.
Le reste des fonctions présentes dans ces classes correspondent aux actions engendrées par les interactions de l'utilisateur avec l'interface ou aux différentes fonctionnalités de localisations GPS pour les fenêtre contenant une carte.
A chacune de ces classes est associé un fichier xml décrivant le design et les éléments des fenêtre (noms, actions engendrées, positions, taille, style, ...).
Ces fichiers sont situés dans le dossier *app/src/main/java/res/layout*.
Les images utilisées par ces designs se trouvent dans les dossiers *app/src/main/java/res/drawable-...*.

La plupart des autres fichiers sont des fichiers générés par Android Studio.

Le ficher .apk se situe dans le dossier *app/build/outputs/apk* et est généré à chaque build

# Améliorations éventuelles à apporter

- Implémenter plus de quêtes, si possibles intéréssantes, avec des prérequis et des enchaînements cohérents
- Ajouter un maximum d'arbres, bien localisés et qualifiés, à la base de données
- Charger les quêtes et les arbres à partir de fichier externes plutôt que de les créer en dur dans le contrôleur
- Créer un document éducatif décrivant les différents arbres impliqués par l'application, pour aider les enfants à se repérer pour les "quêtes force" et éventuellement l'intégrer à l'application
- Changer l'ORM ou la façon de stocker les quêtes terminées pour leur persistance (SugarRecord n'aime pas les ArrayLists)
- Changer les images non libres de droits par des images libres de droits dans l'optique d'ouvrir l'application au reste du monde (ils ne savent pas encore ce qu'ils ratent)
