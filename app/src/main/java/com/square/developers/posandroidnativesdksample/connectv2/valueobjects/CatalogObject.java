package com.square.developers.posandroidnativesdksample.connectv2.valueobjects;

public class CatalogObject extends Base {
     public String type;
     public String id;
     public String updated_at;
     public Integer version;
     public Boolean is_deleted;
     public Integer[] catalog_v1_ids;
     public Boolean present_at_all_locations;
     public String[] present_at_location_ids;
     public String[] absent_at_location_ids;
     public CatalogItem item_data;
     public CatalogCategory  category_data;
     public CatalogItemVariation item_variation_data;
     public CatalogTax tax_data;
     public CatalogDiscount discount_data;
     public CatalogModifierList modifier_list_data;
     public CatalogModifier modifier_data;

}
