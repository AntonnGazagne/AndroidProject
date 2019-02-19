package fr.isen.twibook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


public class CommentairesAdapter(var Commentaires: Array<Commentaires>) : RecyclerView.Adapter<CommentairesAdapter.MyHolder>() {

    //VIEWHOLDER IS INITIALIZED
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.commentaire_template, null)
        return MyHolder(v)
    }

    //DATA IS BOUND TO VIEWS
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val commentaires = Commentaires[position]

        holder.AuthorView.text = commentaires.auteur
        holder.CommentaireView.text = commentaires.commentaire

    }

    override fun getItemCount(): Int {
        return Commentaires.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var AuthorView: TextView
        var CommentaireView: TextView
        var photoView: ImageView


        init {

            AuthorView = itemView.findViewById(R.id.AuthorView)
            CommentaireView = itemView.findViewById(R.id.CommentaireView)
            photoView = itemView.findViewById(R.id.photoView)
        }
    }
}