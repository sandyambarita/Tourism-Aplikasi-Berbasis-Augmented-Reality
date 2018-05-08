package praktikum.develops.a11414001.digitourapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.activity.DaftarCheckpointActivity;
import praktikum.develops.a11414001.digitourapplication.activity.DaftarWisataActivity;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

public class LokasiAdapter extends RealmRecyclerViewAdapter<ModelLocation, LokasiAdapter.ViewHolder> {
    Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_daftarlokasi, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelLocation obj = getItem(position);
        String nmLocation = obj.getLocation_name();
        String city_name = obj.getCity_name();
        String imgLocation = obj.getPath_gambar();


        holder.location_name.setText(" " + nmLocation+", "+city_name );
        Glide.with(mContext).load(RESTClient.URL+"/pa3ws/digitour/image/"+obj.getPath_gambar()).into(holder.image_location);

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    //Toast.makeText(mContext, "Hasil id Lokasi "+obj.getLocation_id(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext.getApplicationContext(), DaftarCheckpointActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("locationId", String.valueOf(obj.getLocation_id()));
                    mContext.startActivity(intent);
                }catch (Exception e)
                {
                    Log.d("hasil error",e.getMessage());
                }



            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView location_name;
        ImageView image_location;
        public CardView card_view;

        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewLokasi);
            location_name = (TextView) view.findViewById(R.id.namaLokasi);
            image_location = (ImageView) view.findViewById(R.id.imageLokasi);

        }
    }

    public LokasiAdapter(RealmResults<ModelLocation> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
       //layoutInflater = LayoutInflater.from(context);
    }

}

