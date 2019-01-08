package hongyu315.com.smart2.bean;

public class GoodsInfo
{
    private String color;
    private int count;
    private String desc;
    private int goodsImg;
    private String id;
    private String imageUrl;
    private boolean isChoosed;
    private String name;
    private int postion;
    private double price;
    private double prime_price;
    private String size;

    public GoodsInfo() {}

    public GoodsInfo(String paramString1,
                     String paramString2,
                     String paramString3,
                     double paramDouble1,
                     double paramDouble2,
                     String paramString4,
                     String paramString5,
                     int paramInt1,
                     int paramInt2)
    {
        this.id = paramString1;
        this.name = paramString2;
        this.desc = paramString3;
        this.price = paramDouble1;
        this.prime_price = paramDouble2;
        this.count = paramInt2;
        this.color = paramString4;
        this.size = paramString5;
        this.goodsImg = paramInt1;
    }

    public String getColor()
    {
        return this.color;
    }

    public int getCount()
    {
        return this.count;
    }

    public String getDesc()
    {
        return this.desc;
    }

    public int getGoodsImg()
    {
        return this.goodsImg;
    }

    public String getId()
    {
        return this.id;
    }

    public String getImageUrl()
    {
        return this.imageUrl;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPostion()
    {
        return this.postion;
    }

    public double getPrice()
    {
        return this.price;
    }

    public double getPrime_price()
    {
        return this.prime_price;
    }

    public String getSize()
    {
        return this.size;
    }

    public boolean isChoosed()
    {
        return this.isChoosed;
    }

    public void setChoosed(boolean paramBoolean)
    {
        this.isChoosed = paramBoolean;
    }

    public void setColor(String paramString)
    {
        this.color = paramString;
    }

    public void setCount(int paramInt)
    {
        this.count = paramInt;
    }

    public void setDesc(String paramString)
    {
        this.desc = paramString;
    }

    public void setGoodsImg(int paramInt)
    {
        this.goodsImg = paramInt;
    }

    public void setId(String paramString)
    {
        this.id = paramString;
    }

    public void setImageUrl(String paramString)
    {
        this.imageUrl = paramString;
    }

    public void setName(String paramString)
    {
        this.name = paramString;
    }

    public void setPostion(int paramInt)
    {
        this.postion = paramInt;
    }

    public void setPrice(double paramDouble)
    {
        this.price = paramDouble;
    }

    public void setPrime_price(double paramDouble)
    {
        this.prime_price = paramDouble;
    }

    public void setSize(String paramString)
    {
        this.size = paramString;
    }
}
