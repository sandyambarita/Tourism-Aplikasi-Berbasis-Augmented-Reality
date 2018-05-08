package praktikum.develops.a11414001.digitourapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievement;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

/**
 * Created by Sandy on 6/7/2017.
 */

public class AchievementAdapter extends RealmRecyclerViewAdapter<ModelAchievement, AchievementAdapter.ViewHolder> {
    Context mContext;

    @Override
    public AchievementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_achievement, parent, false);

        return new AchievementAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AchievementAdapter.ViewHolder holder, int position) {
        final ModelAchievement obj = getItem(position);
        String nmhadiah = obj.getHadiah();
        String nmtempat = obj.getCheckpoint_name();
        String nmtype = obj.getType_name();

        holder.hadiah.setText("Hadiah : " + nmhadiah);
        //holder.type.setText("Tipe : " + nmtype);
        holder.tempat.setText("Lokasi : " + nmtempat);
        Glide.with(mContext).load(RESTClient.URL+"/pa3ws/digitour/image/"+obj.getPath_gambar()).into(holder.path_gambar);

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView hadiah, tempat, type;
        ImageView path_gambar;
        public CardView card_view;

        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewAchievement);
            hadiah= (TextView) view.findViewById(R.id.hadiahAchievement);
            tempat = (TextView) view.findViewById(R.id.lokasiAchievement);
          //  type = (TextView) view.findViewById(R.id.challenge);
            path_gambar =(ImageView) view.findViewById(R.id.imageAchievement);

        }
    }

    public AchievementAdapter(RealmResults<ModelAchievement> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }

}
