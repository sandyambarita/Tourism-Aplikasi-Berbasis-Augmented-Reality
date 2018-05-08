package praktikum.develops.a11414001.digitourapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;
import praktikum.develops.a11414001.digitourapplication.R;
import praktikum.develops.a11414001.digitourapplication.model.ModelLeaderboard;

/**
 * Created by Sandy on 6/7/2017.
 */

public class LeaderboardAdapter extends RealmRecyclerViewAdapter<ModelLeaderboard, LeaderboardAdapter.ViewHolder> {
    Context mContext;

    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tampilan_leaderboard, parent, false);

        return new LeaderboardAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LeaderboardAdapter.ViewHolder holder, int position) {
        final ModelLeaderboard obj = getItem(position);
        String nmleaderboard = obj.getUser();
        String nmcheck = obj.getCheckpoint_name();
        String nmtype = obj.getChallenge_type_name();
        int nmscore = obj.getScore();
        String nmdate = obj.getDate_time();

        Log.e("nama typenya = ",""+obj.getChallenge_type_name());

        holder.nama.setText("" + nmleaderboard );
        holder.tempat.setText("" + nmcheck );
        holder.type.setText("" + nmtype);
        holder.score.setText("" + nmscore );
        holder.datetime.setText("" + nmdate);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama, tempat, type, score, datetime ;
        public TableLayout table_view;

        ViewHolder(View view){
            super(view);

            table_view = (TableLayout) itemView.findViewById(R.id.table_layout);
            nama = (TextView) view.findViewById(R.id.namaLeaderboard);
            tempat = (TextView) view.findViewById(R.id.tempatLeaderboard);
            type = (TextView) view.findViewById(R.id.typeLeaderboard);
            score = (TextView) view.findViewById(R.id.scoreLeaderboard);
            datetime = (TextView) view.findViewById(R.id.datetimeLeaderboard);

        }
    }

    public LeaderboardAdapter(RealmResults<ModelLeaderboard> data, Context mContext){

        super(data, true);
        this.mContext = mContext;
        //layoutInflater = LayoutInflater.from(context);
    }

}


