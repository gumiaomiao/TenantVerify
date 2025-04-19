//租户最多支持五级
public class MyTenant {
    private TenantManager manager = new TenantManager("1", "Root Tenant");
    private Tenant rootTenant = manager.getRootTenant();

    //生成租户并添加云资源，并按照租户体系，为规划总院-数智研究院-基础中心添加云资源
    public void addTenants() {
        // 添加二级租户：规划总院
        Tenant planningInstitute = new Tenant("2", "规划总院", 2, rootTenant);
        rootTenant.addChildTenant(planningInstitute);

        // 添加三级租户：数智研究院
        Tenant digitalResearchInstitute = new Tenant("3", "数智研究院", 3, planningInstitute);
        planningInstitute.addChildTenant(digitalResearchInstitute);

        // 添加四级租户：基础中心
        Tenant basicCenter = new Tenant("4", "基础中心", 4, digitalResearchInstitute);
        digitalResearchInstitute.addChildTenant(basicCenter);

        // 添加四级租户：算力项目组
        Tenant computingPowerProjectGroup = new Tenant("5", "算力项目组", 4, digitalResearchInstitute);
        digitalResearchInstitute.addChildTenant(computingPowerProjectGroup);

        // 为基础中心和算力项目组分别添加 10T 的存储云资源
        Tenant targetBasicCenter = manager.findTenantByName("基础中心");
        Tenant targetComputingPowerProjectGroup = manager.findTenantByName("算力项目组");
        if (targetBasicCenter != null) {
            targetBasicCenter.addResource("10T 存储云资源");
        }
        if (targetComputingPowerProjectGroup != null) {
            targetComputingPowerProjectGroup.addResource("10T 存储云资源");
        }

        // 打印各级租户及其资源
        printTenantResources(rootTenant);

    }

    //打印各级租户及其资源
    private static void printTenantResources(Tenant tenant) {
        System.out.println("租户 " + tenant.getName() + " 的资源: " + tenant.getResources());
        for (Tenant child : tenant.getChildren()) {
            printTenantResources(child);
        }
    }
    /**
    private boolean addTenant(Tenant parent, String childId, String childName, String successMessage) {
        boolean added = manager.addTenant(parent, childId, childName);
        if (added && successMessage != null) {
            System.out.println(successMessage);
        }
        return added;
    }
     */

    public static void main(String[] args) {
        MyTenant myTenant = new MyTenant();
        myTenant.addTenants();
    }
}
