package com.example.wkl_android.base;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 简易的BaseAdapter,配合DataBinding更加美味。。
 *
 * @author zhangyangyang
 * @date 2019/4/26
 */
public abstract class BaseAdapter<T extends BaseAdapter.BaseHolder, D> extends RecyclerView.Adapter<BaseAdapter.BaseHolder> implements View.OnClickListener {
    protected List<D> dataList;
    protected Context context;
    protected ItemClickListener itemClickListener;
    private static final int EMPTY_VIEW = 0xFF;


    public BaseAdapter(List<D> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    protected T createBaseViewHolder(View view) {
        Class temp = getClass();
        Class z = null;
        while (z == null && null != temp) {
            z = getInstancedGenericKClass(temp);
            temp = temp.getSuperclass();
        }
        T k;
        // 泛型擦除会导致z为null
        if (z == null) {
            k = (T) new BaseHolder(view);
        } else {
            k = createGenericKInstance(z, view);
        }
        return k != null ? k : (T) new BaseHolder(view);
    }

    private T createGenericKInstance(Class z, View view) {
        try {
            Constructor constructor;
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(this, view);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (T) constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Class getInstancedGenericKClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class tempClass = (Class) temp;
                    if (BaseHolder.class.isAssignableFrom(tempClass)) {
                        return tempClass;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==EMPTY_VIEW&&emptyView!=null){
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
           emptyView.setLayoutParams(layoutParams);
          return new EmptyHolder(emptyView);
        }
        View v = LayoutInflater.from(context).inflate(getLayout(viewType), parent, false);
        return createBaseViewHolder(v);
    }


    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (position==0){
            if (dataList==null||dataList.size()==0){
                return;
            }
        }
       /* if (position==0&&(dataList==null||dataList.size()==0)){
            return;
        }*/
        holder.itemView.setTag(dataList.get(position));
        holder.itemView.setOnClickListener(this);
        onBindViewHolder((T) holder, position, dataList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            if (dataList == null || dataList.size() == 0) {
                if (emptyView != null) {
                    return EMPTY_VIEW;
                }
            }
        }
        return super.getItemViewType(position);
    }

    protected abstract void onBindViewHolder(T holder, int position, D itemData);

    public abstract @LayoutRes
    int getLayout(int type);

    @Override
    public int getItemCount() {
        int count = dataList == null ? 0 : dataList.size();
        if (count == 0 && emptyView != null) {
            return 1;
        }
        return count;
    }

    public static class BaseHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        public B binding;

        protected BaseHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

     class EmptyHolder extends BaseHolder<ViewDataBinding>{

         protected EmptyHolder(View itemView) {
             super(itemView);
         }
     }
    /**
     * 刷新数据，该方法会刷新整个RecyclerView 效率不高，后期再优化
     *
     * @param data 新的数据
     */
    public void setData(List<D> data) {
        dataList = data;
        notifyDataSetChanged();
    }

    public void addData(List<D> data) {
        if (dataList != null) {
            if (data!=null){
                dataList.addAll(data);
                notifyDataSetChanged();
            }
        } else {
            setData(data);
        }
    }

    public List<D> getData() {
        return dataList;
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.itemClick(v);
        }
    }

    View emptyView;

    public void setEmptyView(View view) {
        emptyView = view;
    }

    public void setEmptyView(@LayoutRes int layoutId) {
        setEmptyView(LayoutInflater.from(context).inflate(layoutId, null));
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void itemClick(View itemView);
    }
}
