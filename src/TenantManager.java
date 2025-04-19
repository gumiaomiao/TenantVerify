public class TenantManager {
    private Tenant rootTenant;

    public TenantManager(String rootId, String rootName) {
        this.rootTenant = new Tenant(rootId, rootName, 1, null);
    }

    public Tenant getRootTenant() {
        return rootTenant;
    }

    // 根据租户 ID 查找租户
    public Tenant findTenantById(String id) {
        return findTenantById(rootTenant, id);
    }

    private Tenant findTenantById(Tenant tenant, String id) {
        if (tenant.getId().equals(id)) {
            return tenant;
        }
        for (Tenant child : tenant.getChildren()) {
            Tenant found = findTenantById(child, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    // 根据租户名称查找租户
    public Tenant findTenantByName(String name) {
        return findTenantByName(rootTenant, name);
    }

    private Tenant findTenantByName(Tenant tenant, String name) {
        if (tenant.getName().equals(name)) {
            return tenant;
        }
        for (Tenant child : tenant.getChildren()) {
            Tenant found = findTenantByName(child, name);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
