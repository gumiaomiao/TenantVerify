import java.util.ArrayList;
import java.util.List;

public class MyOrganization {
    // 创建中石油总部
    public OrganizationUnit  addsOrganization() {
        OrganizationUnit cnpcHeadquarters = new OrganizationUnit("中石油总部");

        // 创建二级单位
        OrganizationUnit daqingOilField = new OrganizationUnit("大庆油田");
        OrganizationUnit changqingOilField = new OrganizationUnit("长庆油田");
        OrganizationUnit tarimOilField = new OrganizationUnit("塔里木油田");
        OrganizationUnit xinjiangOilField = new OrganizationUnit("新疆油田");
        OrganizationUnit southwestOilField = new OrganizationUnit("西南油田");
        OrganizationUnit explorationInstitute = new OrganizationUnit("勘探院");
        OrganizationUnit safetyAndEnvironmentInstitute = new OrganizationUnit("安环院");
        OrganizationUnit digitalIntelligenceResearchInstitute = new OrganizationUnit("数智研究院");

        // 将二级单位添加到总部
        cnpcHeadquarters.addSubUnit(daqingOilField);
        cnpcHeadquarters.addSubUnit(changqingOilField);
        cnpcHeadquarters.addSubUnit(tarimOilField);
        cnpcHeadquarters.addSubUnit(xinjiangOilField);
        cnpcHeadquarters.addSubUnit(southwestOilField);
        cnpcHeadquarters.addSubUnit(explorationInstitute);
        cnpcHeadquarters.addSubUnit(safetyAndEnvironmentInstitute);
        cnpcHeadquarters.addSubUnit(digitalIntelligenceResearchInstitute);

        // 为二级单位添加三级单位示例
        daqingOilField.addSubUnit(new OrganizationUnit("大庆采油一厂"));
        daqingOilField.addSubUnit(new OrganizationUnit("大庆采油二厂"));

        changqingOilField.addSubUnit(new OrganizationUnit("长庆第一采油厂"));
        changqingOilField.addSubUnit(new OrganizationUnit("长庆第二采油厂"));

        // 打印组织结构
        cnpcHeadquarters.printStructure(0);

        return cnpcHeadquarters;

    }

    //申请云资源方法
    public void applyResource()
    {

        List<String> records = new ArrayList<>();
        //每次申请云资源之前，获取单位当前所有组织
        OrganizationUnit cnpcHeadquarters = addsOrganization();

        // 手动选择一个组织单位
        String targetName = "大庆油田";
        OrganizationUnit selectedUnit = OrganizationUnit.selectOrganizationUnit(cnpcHeadquarters, targetName);

        // 申请存储云资源，applyingUnits列表中的组织，每一个都申请10TB的存储空间
        List<OrganizationUnit> applyingUnits = new ArrayList<>();
        String cloudResource = "10TB 存储空间";

        //大庆油田、大庆采油一厂、大庆采油二厂同时申请云资源
        applyingUnits.add(new OrganizationUnit("大庆油田"));
        applyingUnits.add(new OrganizationUnit("大庆采油一厂"));
        applyingUnits.add(new OrganizationUnit("大庆采油二厂"));

        
        //申请云资源之前，先手动选择一个组织结构，逻辑有问题，太困了明天改！！
        for(OrganizationUnit unit: applyingUnits) {
            if (selectedUnit != null) {
                applyingUnits.add(selectedUnit);
            }
            records = OrganizationUnit.applyForCloudResources(applyingUnits, cloudResource);
        }

        System.out.println("\n云资源申请记录：");
        for (String record : records) {
            System.out.println(record);
        }
    }


    public static void main(String[] args) {
        MyOrganization myOrganization = new MyOrganization();
        myOrganization.addsOrganization();


    }

}
