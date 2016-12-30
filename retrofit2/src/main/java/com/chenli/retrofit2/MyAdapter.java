package com.chenli.retrofit2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

/**
 * @author cl
 * @创建时间 2016/12/29 22:53
 * @描述 ${todo}
 * @版本 $$Rev$$
 * @更新者 $$Author$$
 */


public class MyAdapter extends BaseAdapter {
   private Context context;
    private List<Tngou.TngouEntity> list;

    public MyAdapter(Context context, List<Tngou.TngouEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        Tngou.TngouEntity tngouEntity = list.get(position);
        holder.title.setText(tngouEntity.getTitle());
        String date = new SimpleDateFormat().format(tngouEntity.getTime());

        //glide加载图片
//        Glide.with(context).load("http://tnfs.tngou.net/image"+tngouEntity.getImg())
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_error_page)
//                .into(holder.image);

        //picasso加载图片
//         Picasso.with(context).load("http://tnfs.tngou.net/image"+tngouEntity.getImg())
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.ic_error_page)
//                .into(holder.image);

        //xutils加载图片
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setFailureDrawableId(R.mipmap.ic_error_page)
                .build();
        x.image().bind(holder.image, "http://tnfs.tngou.net/image"+tngouEntity.getImg(), imageOptions);

        return convertView;
    }

    public void addAll(Collection<? extends Tngou.TngouEntity> conllection){
        list.addAll(conllection);
        notifyDataSetChanged();
    }

    static class ViewHolder{



        private TextView title;
        private ImageView image;
        private TextView count;

        public ViewHolder(View item) {
            image = ((ImageView) item.findViewById(R.id.item_image));
            title = ((TextView) item.findViewById(R.id.tv_title));
            count = ((TextView) item.findViewById(R.id.tv_count));
        }
    }
}
