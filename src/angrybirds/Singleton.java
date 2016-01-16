/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package angrybirds;

/**
 *
 * @author youdelice
 */
public class Singleton {
    private static Singleton instance = null;
   private Singleton() {
      // Exists only to defeat instantiation.
   }
   public static Singleton getInstance() {
      if(instance == null) {
         instance = new Singleton();
      }
      return instance;
   }
}
