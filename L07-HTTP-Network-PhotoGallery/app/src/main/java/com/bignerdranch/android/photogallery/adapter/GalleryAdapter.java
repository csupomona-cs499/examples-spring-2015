package com.bignerdranch.android.photogallery.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bignerdranch.android.photogallery.GalleryItem;
import com.bignerdranch.android.photogallery.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by yusun on 4/19/15.
 */
public class GalleryAdapter extends ArrayAdapter<GalleryItem> {

    private List<GalleryItem> objects;
    private Context context;

    public GalleryAdapter(Context context, List<GalleryItem> objects) {
        super(context, R.layout.gallery_image_item, objects);
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.gallery_image_item, parent, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.photoImageView);
        GalleryItem item = objects.get(position);

        Picasso.with(context).load(item.getUrl()).into(imageView);
        //new GetImages(item.getUrl(), imageView).execute();

        return view;
    }

    // the following code can be replaced by Picasso library
    private class GetImages extends AsyncTask<Object, Object, Object> {
        private String requestUrl;
        private ImageView view;
        private Bitmap bitmap ;

        private GetImages(String requestUrl, ImageView view) {
            this.requestUrl = requestUrl;
            this.view = view;
        }

        @Override
        protected Object doInBackground(Object... objects) {
            try {
                URL url = new URL(requestUrl);
                URLConnection conn = url.openConnection();
                bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (Exception ex) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            view.setImageBitmap(bitmap);
        }
    }
}
