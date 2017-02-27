public class Entry {
   private String key; 
   private String data;
   
   public Entry (String k, String d){
      key = k;
      data = d;
   }
   
   public String getKey(){
      return key;
   }
   
   public String getData(){
      return data;
   }
   
   public void setData(String d){
      data = d;
   }

}