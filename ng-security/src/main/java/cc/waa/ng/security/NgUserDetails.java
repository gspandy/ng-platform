/*
 * Project:    waa新一代框架的安全模块
 * 
 * FileName:   NgUserDetails.java
 * CreateTime: 2016-06-16 14:20:36
 */
package cc.waa.ng.security;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cc.waa.ng.base.data.obj.Role;
import cc.waa.ng.base.data.obj.User;

/**
 * @author  Kinwaa
 *
 * @version 0.0.1
 * @since   0.0.1
 */
public class NgUserDetails
	extends AbstractUserDetails
	implements UserDetails
{

	/** serialVersionUID */
	private static final long serialVersionUID = 6595985597950048610L;



	private String username;

	private final Collection<SimpleGrantedAuthority> authorities;

	/** 默认构建函数（避免Session序列化时出错） */
	public NgUserDetails()
	{
		super();

		this.authorities = new HashSet<SimpleGrantedAuthority>();
	}

	/**
	 * @param user
	 */
	public NgUserDetails(User user, String username)
	{
		super(user);

		this.username = username;
		this.authorities = new HashSet<SimpleGrantedAuthority>();

		for (Role role : this.user.getProfiles().keySet())
			this.authorities.add(new SimpleGrantedAuthority(join("ROLE_", role.getCode())));
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword()
	{
		// 用userId作为验证密码的依据
		// 在PasswordEnoder里面在获取相应用户来验证密码是否正确
		return this.user.getId();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername()
	{
		return this.username;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired()
	{
		return !this.user.isExpired();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked()
	{
		return !this.user.isLocked();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;	// TODO 考虑可否用于密码过期后要求强制更换的功能
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled()
	{
		return !this.user.isDeleted();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		if (this.user == null)
			return 0;

		return this.user.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this.user == null || !(obj instanceof NgUserDetails))
			return false;

		return this.user.equals(((NgUserDetails) obj).user);
	}

}
