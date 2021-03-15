package test.factory.issue1041_dp;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;
import java.util.Objects;

public class FactoryAnnotatedConstructorExample {
  public static final String DP = "dp";


  static List<FactoryAnnotatedConstructorExample> objects = Lists.newArrayList();

  private int data;

//  @Factory(dataProvider = "dp")
//  public FactoryAnnotatedConstructorExample(int data) {
//    this.data = data;
//    addInstance(this);
//  }

  private static void addInstance(FactoryAnnotatedConstructorExample instance) {
    objects.add(instance);
  }

  @DataProvider(name = DP)
  public static Object[][] getData() {
    return new Object[][]{
        {123},
        {234}
    };
  }

  @Test(dataProvider = DP)
  public void testMethod(int data) {
    Assert.assertTrue(data > 0);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FactoryAnnotatedConstructorExample that = (FactoryAnnotatedConstructorExample) o;
    return data == that.data;
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }
}
