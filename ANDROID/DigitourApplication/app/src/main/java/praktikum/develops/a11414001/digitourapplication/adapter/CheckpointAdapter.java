package praktikum.develops.a11414001.digitourapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.activity.DaftarCheckpointActivity;
import praktikum.develops.a11414001.digitourapplication.activity.DeskripsiWisataActivity;
import praktikum.develops.a11414001.digitourapplication.model.ModelCheckpoint;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

/**
 * Created by Sandy on 4/28/2017.
 */

public class CheckpointAdapter extends RealmRecyclerViewAdapter<ModelCheckpoint, CheckpointAdapter.ViewHolder> {
    Context mContext;

    @Override
    public CheckpointAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_daftarcheckpoint, parent, false);

        return new CheckpointAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CheckpointAdapter.ViewHolder holder, int position) {
        final ModelCheckpoint obj = getItem(position);
        String nmCheckpoint = obj.getCheckpoint_name();



        holder.checkpoint_name.setText(" " + nmCheckpoint );
        Glide.with(mContext).load(RESTClient.URL+"/pa3ws/digitour/image/"+obj.getPath_gambarpoint()).into(holder.image_checkpoint);

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //Toast.makeText(mContext, "Hasil id Lokasi "+obj.getLocation_id(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext.getApplicationContext(), DeskripsiWisataActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("checkpointId", String.valueOf(obj.getCheckpoint_id()));
                    mContext.startActivity(intent);
                }catch (Exception e)
                {
                    Log.d("hasil error",e.getMessage());
                }

            }
        });


    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView checkpoint_name;
        ImageView image_checkpoint;
        public CardView card_view;
        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewCheckpoint);
            checkpoint_name = (TextView) view.findViewById(R.id.namaCheckpoint);
            image_checkpoint = (ImageView) view.findViewById(R.id.imageCheckpoint);

        }
    }

    public CheckpointAdapter(RealmResults<ModelCheckpoint> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }

}

