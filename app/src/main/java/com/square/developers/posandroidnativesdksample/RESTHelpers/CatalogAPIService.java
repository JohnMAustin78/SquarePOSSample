package com.square.developers.posandroidnativesdksample.RESTHelpers;

import com.square.developers.posandroidnativesdksample.connectv2.valueobjects.CatalogObject;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CatalogAPIService {

  @GET("/{version}/catalog/object/{object_id}")
  Call<CatalogObject> getCatalogObject(@Path("version") String version, @Path("object_id") String object_id);

  @GET("/{version}/catalog/list")
  Call<List<CatalogObject>> listCatalogObject(@Path("version") String version);

  @DELETE("/{version}/catalog/object/{object_id}")
  Call<CatalogObject> deleteCatalogObject(@Path("version") String version, @Path("object_id") String object_id);
}
