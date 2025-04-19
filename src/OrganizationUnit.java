import java.util.ArrayList;
import java.util.List;

//单位组织结构
public class OrganizationUnit {
    private String name;
    private List<OrganizationUnit> subUnits;
    //存储该单位的云资源申请记录。
    private List<String> cloudResourceApplications;


    public OrganizationUnit(String name) {
        this.name = name;
        this.subUnits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<OrganizationUnit> getSubUnits() {
        return subUnits;
    }

    public void addSubUnit(OrganizationUnit subUnit) {
        subUnits.add(subUnit);
    }

    // 递归打印组织结构
    public void printStructure(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }
        System.out.println(indent + name);
        for (OrganizationUnit subUnit : subUnits) {
            subUnit.printStructure(level + 1);
        }
    }



// 申请云资源的方法
    public static List<String> applyForCloudResources(List<OrganizationUnit> units, String cloudResource) {
        List<String> applicationRecords = new ArrayList<>();
        for (OrganizationUnit unit : units) {
            unit.cloudResourceApplications.add(cloudResource);
            applicationRecords.add(unit.getName() + " 申请了 " + cloudResource);
        }
        return applicationRecords;
    }

    // 获取该单位的云资源申请记录
    public List<String> getCloudResourceApplications() {
        return cloudResourceApplications;
    }

    // 选择组织单位，申请资源时，任意选择一个组织单位
    public static OrganizationUnit selectOrganizationUnit(OrganizationUnit root, String targetName) {
        if (root.getName().equals(targetName)) {
            return root;
        }
        for (OrganizationUnit subUnit : root.getSubUnits()) {
            OrganizationUnit found = selectOrganizationUnit(subUnit, targetName);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
