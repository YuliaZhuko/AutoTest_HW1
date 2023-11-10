package data.menu;

public enum MenuItemData {
  Courses("Программирование");

  private String name;

  MenuItemData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
