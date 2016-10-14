package server.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {



	private String filterChainDefinitions;
	
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Section getObject() throws BeansException {
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
		if (CollectionUtils.isEmpty(section)) {
			section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		}

		return section;
	}

	@Override
	public Class<Section> getObjectType() {
		return Section.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
