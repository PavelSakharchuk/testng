package test.factory.issue1041_dp_f;

import java.util.List;
import java.util.Objects;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

public class FactoryAnnotatedConstructorExample {
  static List<FactoryAnnotatedConstructorExample> objects = Lists.newArrayList();

  private int data;

  @Factory(dataProvider = "dp")
  public FactoryAnnotatedConstructorExample(int data) {
    this.data = data;
    addInstance(this);
  }

  private static void addInstance(FactoryAnnotatedConstructorExample instance) {
    objects.add(instance);
  }

//  @DataProvider(name = "dp", indices = {1})
  @DataProvider(name = "dp")
  public static Object[][] getData() {
    return new Object[][]{
        {123},
        {234},
        {345}
    };
  }

  @Test
  public void testMethod() {
    System.out.println(data);
    Assert.assertTrue(data > 1);
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
