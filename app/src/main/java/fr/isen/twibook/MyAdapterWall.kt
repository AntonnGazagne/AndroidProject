package fr.isen.twibook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MyAdapterWall(private var articles: ArrayList<Article>) : RecyclerView.Adapter<MyAdapterWall.MyHolder>() {
    //VIEWHOLDER IS INITIALIZED
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_my_adapter_wall, null)
        return MyHolder(v)

    }

    //DATA IS BOUND TO VIEWS
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val article = articles[position]


        val res = "R.drawable" + article.photo
        //val id = resources.getIdentifier(res, "drawable", packageName)
        //holder.img.setImageResource(id)

        holder.DateTxt.text = article.date
        holder.AuteurTxt.text =article.auteur
        holder.DescriptionTxt.text = article.description
        holder.TitreTxt.text = article.titre

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView
        var TitreTxt: TextView
        var DescriptionTxt : TextView
        var AuteurTxt: TextView
        var DateTxt: TextView
        init {
            img = itemView.findViewById(R.id.PhotoAuteur)
            TitreTxt = itemView.findViewById(R.id.TitreArticle)
            DescriptionTxt = itemView.findViewById(R.id.Description)
            AuteurTxt = itemView.findViewById(R.id.Auteur)
            DateTxt = itemView.findViewById(R.id.Date)
        }
    }
}