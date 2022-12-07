[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# 👨‍💻 EES - Projet d'application mobile Nada DABACH et Léo-Paul HAUET

## 📝 Avant propos 🎬

Ce projet a été réalisé dans le cadre de notre cursus ISMIN spécialisation Computer Science. 
L'application permet d'accéder à la liste des établissements du supérieur en France et à travers le monde. 
Cette liste à été procurée par l'API du gouvernement en suivant ce [lien](https://data.opendatasoft.com/explore/dataset/fr-esr-principaux-etablissements-enseignement-superieur%40mesr/api/?disjunctive.type_d_etablissement&sort=uo_lib&disjunctive.typologie_d_universites_et_assimiles)

Nous tenons à remercier Gaëtan MASSE, notre professeur pour sa patience et ses cours de grande qualité. Il nous a permis de nous entrainer à utiliser git avec Github en plus du développement d'API et d'Application Android.


### 💯 Utilisation 🎯

Pour utiliser notre application il suffit de télécharger la partie android et de la lancer sur un téléphone (physique ou simulé)

Une fois l’application lancée vous pouvez voir la liste des établissements par ordre alphabétique. Une image de la lettre de leur vague contractuelle sera aussi affichée si l’information a été donnée par l’API de l’Etat. Dans le cas contraire une image de Marianne. 
Si vous cliquez sur une école vous avez accès à plus de ses informations. Comme l’adresse, la catégorie (Public/Privé💲), etc…
Nous avons aussi implémenté une recherche par département, il suffit de rentrer Paris pour avoir accès à l’ensemble des établissements à Paris. Pour revenir en arrière il faut appuyer sur le bouton Home de la bottom navigation bar. Attention il faut mettre la majuscule aux départements recherchés

Ensuite vous aurez aussi la possibilité d’ajouter une école avec un bouton ➕
Il suffit de renseigner le libellé de l’établissement ainsi que sa position géographique et son code postal. Cet ajout vient modifier la base de données de l’API. C’est-à-dire que même après un redémarrage, l’école rentrée sera encore présente. Ceci se déroule dans un autre fragment.

Vous avez ensuite un fragment « About » avec une courte description de l’application ainsi que le lien vers notre API.

Enfin, le fragment de la carte. Toutes les écoles sont affichées sur la carte du monde. Lorsque vous appuyez sur un des marqueurs 📍 vous pouvez voir le libellé de l’école. 

Pour conclure une dernière fonctionnalité, le bouton favori, représenté par une étoile ☆ ⭐ 
La fonctionnalité marche sur l’application mais la requête PUT ne passe pas à l’API. Les favoris ne sont donc pas conservés lors du redémarrage.


### 🌐 Fonctionnalités de l'API 📲

Notre API School-LHT-NDH:
•	Télécharge les données au démarrage
•	Dispose d’une requête GET qui permet de récupérer l’ensemble des données /schools 🗃️
•	Dispose d’une requête GET qui permet de récupérer une seule donnée 
    /schools/libelléDeL’école 📁
•	Dispose d’une requête PUT qui permet de mettre en favori une donnée 
    /schools/libelléDeL’école ⭐
•	Dispose d’une requête DELETE qui permet d’effacer une école 
    /schools/ libelléDeL’école ❌
•	Dispose d’un endpoint qui permet de rajouter une école 🏫
•	Dispose d’un endpoint de recherche 🔎
•	DISPOSAIT d’une pagination cependant cela posait trop de problème avec l’application ☹️
•	Elle est déployée sur Clever Cloud 🌐

Toutes ces fonctionnalités sont testables avec un navigateur ainsi qu’avec Postman 
Voici le lien de l’[API](https://app-cb40d835-d4b4-407f-83a6-0452ebe04576.cleverapps.io/)


### 📱 Fonctionnalités de l'Application Mobile 🎓

Notre application ESS:

•	Récupère les données de l’API, les affiches sur une liste et sur une carte avec leur position 🌐
•	Permet de mettre des écoles en favori ⭐
•	Est composée de 4 fragments, de 2 activités et d’une toolbar qui permet de rafraichir la liste 🔄
•	Permet d’effectuer une recherche filtrée par département (sur la toolbar) 🔍

😙🧋❤️
