import java.util.ArrayList;
import java.util.List;

public class Tenant {
    private String id;
    private String name;
    private int level;
    private Tenant parent;
    private List<Tenant> children;
    private List<String> resources;


    public Tenant(String id, String name, int level, Tenant parent) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Tenant getParent() {
        return parent;
    }

    public List<Tenant> getChildren() {
        return children;
    }

    public List<String> getResources() {
        return resources;
    }

    // 添加子租户
    public boolean addChildTenant(Tenant child) {
        if (this.level >= 4) {
            System.out.println("已达到最大租户层级，无法添加子租户。");
            return false;
        }
        if (child.getLevel() != this.level + 1) {
            System.out.println("子租户层级设置错误。");
            return false;
        }
        this.children.add(child);
        return true;
    }

    // 为租户添加云资源
    public void addResource(String resource) {
        this.resources.add(resource);
    }
}
