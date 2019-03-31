package net.vince.gists;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

public class Test {

  @org.junit.jupiter.api.Test
  @DisplayName("Switch expression")
  public void testSwitch() {
    final var intValue = 1;
    final var textValue =
        switch (intValue) {
          case 1:
            break "one";
          case 2:
            break "two";
          case 3:
            break "three";
          default:
            break "don't know";
        };

    assertEquals("one", textValue);
  }

  @org.junit.jupiter.api.Test
  @DisplayName("String literals")
  public void testLiteral() {
    final var literal =
        `
          String literals
        `;

    assertTrue(literal.contains("\n"));
  }
}
