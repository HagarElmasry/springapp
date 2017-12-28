import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;

/**
 * 
 */

/**
 * @author hagar.elmasry
 *
 */
public class CustomUserDetailsService implements UserDetailsService {
	
  static final Logger logger= LoggerFactory.logger(CustomUserDetailsService.class);
  
  @Autowired
  private UserService userService;
  
  
  public UserDetails loadUserByUsername(String ssoId) {
  
   User user= userService.findBySSO(ssoId);
   logger.info("User : {}", user);
   if(user==null){
       logger.info("User not found");
       throw new UsernameNotFoundException("Username not found");
   }
   
   return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
           true, true, true, true, getGrantedAuthorities(user));
         
  }
  
  private List<GrantedAuthority> getGrantedAuthorities(User user){
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       
      for(UserProfile userProfile : user.getUserProfiles()){
          logger.info("UserProfile : {}", userProfile);
          authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
      }
      logger.info("authorities : {}", authorities);
      return authorities;
  }
   
  
}