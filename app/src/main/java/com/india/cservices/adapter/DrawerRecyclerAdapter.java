package com.india.cservices.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.india.cservices.R;
import com.india.cservices.common.AppConstants;
import com.india.cservices.common.SharedPreference;
import com.india.cservices.inerfaces.OnItemCLickListner;

/**
 * Created by lab1 on 24/07/17.
 *
 */

public class DrawerRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private final SharedPreference sharedPreference;
    private Context context ;
    private String [] mPlanetTitles;
    private OnItemCLickListner onItemCLickListner ;
    int[] mPlanetImages;
    public DrawerRecyclerAdapter(Context context, String[] mPlanetTitles, int[] mPlanetImages , OnItemCLickListner onItemCLickListner)
    {
        sharedPreference = SharedPreference.getInstance(context);
        this.context = context ;
        this.mPlanetTitles = mPlanetTitles ;
        this.mPlanetImages = mPlanetImages ;
        this.onItemCLickListner = onItemCLickListner;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView ;
        int TYPE_HEADER = 0;
        if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.nav_header_dashboard, parent, false);
            return new DrawerRecyclerAdapter.LIHeader(itemView);
        }else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_nav_drawer, parent, false);
            return new DrawerRecyclerAdapter.DrawerViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LIHeader) {
            LIHeader drawerViewHolder = (LIHeader) holder ;
            drawerViewHolder.name.setText(sharedPreference.getString(AppConstants.USER_NAME));
            drawerViewHolder.email.setText(sharedPreference.getString(AppConstants.MAIL));
           /* byte[] decodedString = Base64.decode(sharedPreference.getString(AppConstants.LOGED_IN_USER_PROFILE_PHOTO).getBytes(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            if (decodedByte != null) {
                Bitmap bMapScaled = Bitmap.createBitmap(decodedByte, 0, 0, decodedByte.getWidth(), (int) (decodedByte.getHeight() / 1.5));
                decodedByte = Bitmap.createScaledBitmap(decodedByte, decodedByte.getWidth(), decodedByte.getHeight(), false);
                drawerViewHolder.profileImg.setImageBitmap(decodedByte);
            }*/

        }else {
            TypedArray imgs ;
            imgs = context.getResources().obtainTypedArray(R.array.drawer_images);
            DrawerViewHolder drawerViewHolder = (DrawerViewHolder)holder ;
            drawerViewHolder.imagView.setImageResource(imgs.getResourceId(position, -1));
            drawerViewHolder.itemName.setText(mPlanetTitles[position]);

        }
    }

    @Override
    public int getItemCount() {
        return mPlanetTitles.length;
    }

    public class DrawerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView itemName;
        private final LinearLayout itemParentView;
        private  ImageView imagView;

        DrawerViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.tv_drawer_item);
            itemParentView = (LinearLayout) itemView.findViewById(R.id.rel_drawer_item);
            imagView = (ImageView) itemView.findViewById(R.id.img_drawer_item);
            itemParentView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemCLickListner.OnItemCLick(getAdapterPosition());
        }
    }


    public class LIHeader extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final de.hdodenhof.circleimageview.CircleImageView profileImg;
        private final TextView name;
        private final TextView email;
        LinearLayout mRelProfile;

        LIHeader(View itemView) {
            super(itemView);
            mRelProfile = (LinearLayout) itemView.findViewById(R.id.rel_profile);
            profileImg = (de.hdodenhof.circleimageview.CircleImageView) itemView.findViewById(R.id.profile_img);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            email = (TextView) itemView.findViewById(R.id.tv_email);
            mRelProfile.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(context,"Profile Click",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
