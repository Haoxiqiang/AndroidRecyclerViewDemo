AndroidRecyclerViewDemo
=======================
[My Blog](http://0.0.0.0:4000/blog/20141103-Android's%20RecyclerView.html)

RecyclerView 是一个更加灵活的ListView.在google的文档中说,这个控件能非常有效的维护数量有限的滚动数据集合,当你的View有用户行为和网络数据交互的需求的时候都建议使用RecyclerView.
## RecyclerView简化了View的显示和数据的处理:

* 布局的定位
* Item项的公共动画,比如增加或者删除的动画

<!-- more -->
要使用RecyclerView你必须指定一个布局管理器和一个适配器(从RecyclerView.Adapter扩展).布局管理器的作用就是提供一个一个位置信息来确定Item的复用与回收,避免了不必要的错误和执行昂贵的性能浪费(findViewById).
RecyclerView提供这些内置的布局管理器:

* LinearLayoutManager 示出了在垂直或水平滚动列表项。
* GridLayoutManager 网格展示的布局。
* StaggeredGridLayoutManager 交错网格展示。

## RecyclerView的动画:
RecyclerView默认启用添加和删除的动画.要自定义这些动画，扩展RecyclerView.ItemAnimator类，并使用RecyclerView.setItemAnimator().引用了一个例子[RecyclerViewItemAnimators](https://github.com/gabrielemariotti/RecyclerViewItemAnimators)

## RecyclerView的点击事件
RecyclerView没有类似ListView中的onItemClickListener,原因是原来的onItemClickListener事件容易让人难以理解,现在的RecyclerView其实并没有严格的行或者列的概念,所以我们在这里使用的是每一个View的点击事件.[Why doesn't RecyclerView have onItemClickListener()?](http://stackoverflow.com/questions/24885223/why-doesnt-recyclerview-have-onitemclicklistener)

## LayoutManager

### LinearLayoutManagerd 默认的的效果就是一个ListView模样的,此外还有一个构造函数`LinearLayoutManager(Context context, int orientation, boolean reverseLayout)``orientation`代表了`HORIZONTAL or VERTICAL`.`reverseLayout`表示是否逆序,在一些需要排序的时候比较好用,前面的这段代码表示2行垂直布局,按照数据次序排序
{% highlight java %}
...
mLayoutManager.setStackFromEnd(true);
...
{% endhighlight %}
如果`setStackFromEnd(true)`,则表示显示底部的项,对于集合改变后的时候不起作用
### GridLayoutManager
{% highlight java %}
...
mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
mRecyclerView.setLayoutManager(mLayoutManager);
dataSet.addAll(Arrays.asList(LETTERS));
...
{% endhighlight %}

GridLayoutManager的构造方法又两种一种是默认的垂直布局的`GridLayoutManager (Context context, int spanCount)`,其中`spanCount`垂直时控制列数,另外一种构造方法是`public GridLayoutManager (Context context, int spanCount, int orientation, boolean reverseLayout)`,同`LinearLayoutManagerd`
