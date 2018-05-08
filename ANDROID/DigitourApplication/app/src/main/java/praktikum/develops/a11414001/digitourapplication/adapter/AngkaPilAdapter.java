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
import praktikum.develops.a11414001.digitourapplication.activity.PilihanGandaActivity;
import praktikum.develops.a11414001.digitourapplication.activity.TrueFalseActivity;
import praktikum.develops.a11414001.digitourapplication.model.ModelLocation;
import praktikum.develops.a11414001.digitourapplication.model.ModelQuestion;
import praktikum.develops.a11414001.digitourapplication.service.RESTClient;

/**
 * Created by Sandy on 5/16/2017.
 */

public class AngkaPilAdapter extends RealmRecyclerViewAdapter<ModelQuestion, AngkaPilAdapter.ViewHolder> {
    Context mContext;

    @Override
    public AngkaPilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_gridpilihan, parent, false);

        return new AngkaPilAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelQuestion obj = getItem(position);
//        int idQuestion = obj.getQuestion_id();
        int noSoal = obj.getNo_soal();
        int challengetypeid = obj.getChallenge_type_id();
        //int tmp = position+1;
        //holder.idQuestion.setText(""+tmp);
        holder.nomorSoal.setText(""+noSoal);

        if(challengetypeid==1) {
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    try {
//                    //Toast.makeText(mContext, "Hasil id Lokasi "+obj.getLocation_id(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext.getApplicationContext(), PilihanGandaActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("checkpointId", String.valueOf(obj.getCheckpoint_id()));
                        intent.putExtra("questionID", String.valueOf(obj.getQuestion_id()));
                        intent.putExtra("noSoal", String.valueOf(obj.getNo_soal()));

                        intent.putExtra("challengeTypeId", String.valueOf(obj.getChallenge_type_id()));
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Log.d("hasil error", e.getMessage());
                    }


                }
            });
        }else if(challengetypeid==2){
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
//                    //Toast.makeText(mContext, "Hasil id Lokasi "+obj.getLocation_id(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext.getApplicationContext(), TrueFalseActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("checkpointId", String.valueOf(obj.getCheckpoint_id()));
                        intent.putExtra("challengeTypeId", String.valueOf(obj.getChallenge_type_id()));
                        intent.putExtra("questionID", String.valueOf(obj.getQuestion_id()));
                        intent.putExtra("noSoal", String.valueOf(obj.getNo_soal()));
                        mContext.startActivity(intent);
                    } catch (Exception e) {
                        Log.d("hasil error", e.getMessage());
                    }


                }
            });
        }else if(challengetypeid==3){
            //untuk augmented realitynya cuy
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView nomorSoal;
        public CardView card_view;

        ViewHolder(View view){
            super(view);

            card_view = (CardView) itemView.findViewById(R.id.cardViewLokasi);
            nomorSoal = (TextView) view.findViewById(R.id.angkapilihan);

        }
    }

    public AngkaPilAdapter(RealmResults<ModelQuestion> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }

}


