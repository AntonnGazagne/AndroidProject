package fr.isen.twibook

data class Article(
    var date: String? = "",
    var auteur: String? = "",
    var titre: String? = "",
    var description: String? = "",
    var commentaires: ArrayList<Commentaires>,
    var liker: ArrayList<Auteur>
)

data class Commentaires(
    var commentaire: String? = "",
    var auteur: String? = "",
    var photo: String? =""
)

data class Auteur(
    var pseudo:String?="",
    var photo: String?="",
    var articles: ArrayList<Article>
)