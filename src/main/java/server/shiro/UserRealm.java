package server.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import server.entity.Role;
import server.model.User;
import server.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> permissions = userService.getUser(username).getRoles().stream().map(Role::getName).collect(Collectors.toSet());
		permissions.forEach(item->System.out.println(item));
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		String username = (String)token.getPrincipal();

//		UserEntity user = userService.findByUsername(username);
		User user = userService.getUser(username);
		String password = new String((char[])token.getCredentials()); //得到密码

		if(user == null) {
			throw new UnknownAccountException();//没找到帐号
		}
		if(!password.equals(user.getPasswd())) {
			throw new IncorrectCredentialsException(); //如果密码错误
		}

		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getName(), //用户名
				user.getPasswd(), //密码
				ByteSource.Util.bytes("salt"),//salt=username+salt
				getName()  //realm name
		);
		return authenticationInfo;
	}
}
