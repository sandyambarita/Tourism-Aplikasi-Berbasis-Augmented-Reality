package praktikum.develops.a11414001.digitourapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.activity.DaftarCheckpointActivity;
import praktikum.develops.a11414001.digitourapplication.model.ModelAbout;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

/**
 * Created by Sandy on 5/22/2017.
 */

public class AboutAdapter extends RealmRecyclerViewAdapter<ModelAbout, AboutAdapter.ViewHolder> {
    Context mContext;

    @Override
    public AboutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_about, parent, false);

        return new AboutAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AboutAdapter.ViewHolder holder, int position) {
        final ModelAbout obj = getItem(position);
        String name = obj.getName();
        int nim = obj.getNim();
        String prodi = obj.getProdi();
        String path_gambar = obj.getPath_gambar();


        holder.name.setText(" " + name);
        holder.nim.setText(" " + nim);
        holder.prodi.setText(" " + prodi);
        Glide.with(mContext).load(RESTClient.URL+"/pa3ws/digitour/image/"+obj.getPath_gambar()).into(holder.path_gambar);

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, nim, prodi;
        ImageView path_gambar;
        public CardView card_view;

        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewAbout);
            name= (TextView) view.findViewById(R.id.namaAbout);
            nim = (TextView) view.findViewById(R.id.nimAbout);
            prodi = (TextView) view.findViewById(R.id.prodiAbout);
            path_gambar =(ImageView) view.findViewById(R.id.imageAbout);

        }
    }

    public AboutAdapter(RealmResults<ModelAbout> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }

}


