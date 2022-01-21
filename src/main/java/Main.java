import java.io.File;
import java.net.URI;

class Main {
  public static void main(String[] args) {

    try {

      // Two absolute paths
      File absolutePath1 = new File("C:\\Users\\Ce pc\\Desktop\\Blog-web-application\\blog\\src\\main\\webapp\\images");
      System.out.println("Absolute Path1: " + absolutePath1);
      File absolutePath2 = new File("C:\\Users\\Ce pc\\Desktop");
      System.out.println("Absolute Path2: " + absolutePath2);

      // convert the absolute path to URI
      URI path1 = absolutePath1.toURI();
      URI path2 = absolutePath2.toURI();

      // create a relative path from the two paths
      URI relativePath = path2.relativize(path1);

      // convert the URI to string
      String path = relativePath.getPath();

      System.out.println("Relative Path: " + path);


    } catch (Exception e) {
      e.getStackTrace();
    }
  }
}