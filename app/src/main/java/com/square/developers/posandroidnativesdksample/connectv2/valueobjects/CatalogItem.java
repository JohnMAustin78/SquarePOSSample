package com.square.developers.posandroidnativesdksample.connectv2.valueobjects;

public class CatalogItem extends Base {
    public String name;
    public String description;
    public String abbreviation;
    public String label_color;
    public Boolean available_online;
    public Boolean available_for_pickup;
    public Boolean available_electronically;
    public String category_id;
    public String[] tax_ids;
    public CatalogItemModifierListInfo modifier_list_info;
    public String image_url;
    public CatalogObject[] variations;
    public String product_type;
    public Boolean skip_modifier_screen;

}
