package simlator.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import s3.crypto.SecurityModuleService;
import s3.crypto.hsm.HostSecurityModuleService;
import steel.share.dictview.DictViewService;
import steel.spring.mdp.annotation.Mdpwired;

import com.bill99.inf.acms.client.core.DynamicConfigStore;

public class RpcSwitchService {

	private static DynamicConfigStore dynamicConfigStore = DynamicConfigStore.instance();

	private static final String MDP_SERVICES = "mdp";
	/**
	 * 安全模块服务
	 */
	private SecurityModuleService securityModuleService;

	private SecurityModuleService mdpSecurityModuleService;

	private HostSecurityModuleService hostSerucityModuleService;

	private HostSecurityModuleService mdpHostSerucityModuleService;

//	private DictViewService dictViewService;

	private DictViewService mdpDictViewService;

	public SecurityModuleService getSecurityModuleService() {
		String serviceSwith = dynamicConfigStore.get("cps.rpc.serviceSwitch");
		if (MDP_SERVICES.equals(serviceSwith)) {
			return mdpSecurityModuleService;
		}
		return securityModuleService;
	}

	@Autowired
	public void setSecurityModuleService(
			@Qualifier("thunder.securityModuleService") SecurityModuleService securityModuleService) {
		this.securityModuleService = securityModuleService;
	}

	@Mdpwired
	public void setMdpSecurityModuleService(SecurityModuleService mdpSecurityModuleService) {
		this.mdpSecurityModuleService = mdpSecurityModuleService;
	}

	public HostSecurityModuleService getHostSerucityModuleService() {
		String serviceSwith = dynamicConfigStore.get("cps.rpc.serviceSwitch");
		if (MDP_SERVICES.equals(serviceSwith)) {
			return mdpHostSerucityModuleService;
		}
		return hostSerucityModuleService;

	}

	@Autowired
	public void setHostSerucityModuleService(
			@Qualifier("thunder.hostSecurityModuleService") HostSecurityModuleService hostSerucityModuleService) {
		this.hostSerucityModuleService = hostSerucityModuleService;
	}

	@Mdpwired
	public void setMdpHostSerucityModuleService(HostSecurityModuleService mdpHostSerucityModuleService) {
		this.mdpHostSerucityModuleService = mdpHostSerucityModuleService;
	}

	public DictViewService getDictViewService() {
		return mdpDictViewService;
	}

//	@Autowired
//	public void setDictViewService(@Qualifier("thunder.dictViewService") DictViewService dictViewService) {
//		this.dictViewService = dictViewService;
//	}

	@Mdpwired
	public void setMdpDictViewService(DictViewService mdpDictViewService) {
		this.mdpDictViewService = mdpDictViewService;
	}

}