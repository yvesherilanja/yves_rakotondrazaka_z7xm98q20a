DESCRIPTION :
* Base de données H2
* Les tables sont créées automatiquement grâce à JPA
* Les tables sont alimentées à partir du fichier src/main/resources/data.sql
* Je n'ai pas utilisé de table "user" et "role" car nous avons seulement besoin d'un utilisateur pour les tests.

DÉROULEMENT DU TEST :
 1- L'authentification est requise pour obtenir le token de type Bearer :
   utiliser le chemin /auth/signin avec les données suivantes :

    {
    "username":"anywr",
    "password":"12345678"
    }

  2- Utiliser le chemin /api/students pour obtenir la liste des étudiants.
   Il est possible d'utiliser les paramètres suivants qui ne sont pas obligatoires :
  * "pageNumber" pour la pagination qui a une valeur par défaut de 1
  * "size" pour le nombre de résultats à afficher qui a une valeur par défaut de 10
  * "class_name" pour filtrer par classe [insensible à la casse]
  * "teacher_name" pour filtrer par le nom complet des professeurs [insensible à la casse et peut être entré dans les deux sens : nom+prénom ou prénom+nom]
  
DOCUMENTATION :
Utilisation de la base de données H2 : Stackoverflow
Le reste est du code que j'utilise régulièrement.
