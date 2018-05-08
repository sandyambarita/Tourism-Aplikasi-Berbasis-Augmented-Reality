package praktikum.develops.a11414001.digitourapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelAchievementUser;

/**
 * Created by Sandy on 6/8/2017.
 */

public class AchievementUserAdapter extends RealmRecyclerViewAdapter<ModelAchievementUser, AchievementUserAdapter.ViewHolder> {
    Context mContext;

    @Override
    public AchievementUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_achievement_user, parent, false);

        return new AchievementUserAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AchievementUserAdapter.ViewHolder holder, int position) {
        final ModelAchievementUser obj = getItem(position);
        String nmhadiah = obj.getHadiah();
        String nmtempat = obj.getCheckpoint_name();
        String nmtype = obj.getChallenge_type_name();


        holder.hadiah.setText("Nama Achieve: " + nmhadiah);
        holder.tempat.setText("Tempat didapat : " + nmtempat);
        holder.type.setText("Dari Tipe Soal : "+ nmtype);

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView hadiah, tempat, type;

        ViewHolder(View view){
            super(view);

            hadiah= (TextView) view.findViewById(R.id.hadiahAchievementUser);
            tempat = (TextView) view.findViewById(R.id.lokasiAchievementUser);
            type = (TextView) view.findViewById(R.id.typeAchievementUser);
        }
    }

    public AchievementUserAdapter(RealmResults<ModelAchievementUser> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }

}
