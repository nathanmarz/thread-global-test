package tgt;

public class TestThread extends Thread {
  public Object global;

  public TestThread(Runnable r, Object g) {
    super(r);
    global = g;
  }
}
