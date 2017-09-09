package com.vinodh.config.test;

/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebMVCInitializer.class, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration*/
public class SpringSecurityTests {/*

	@Autowired
	private WebApplicationContext applicationContext;

	@Autowired
	private Filter springSecurityFilterChain;

	private User user;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		user = new User("bill", "abc123", AuthorityUtils.createAuthorityList("USER"));

		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).addFilters(springSecurityFilterChain).build();

	}

	 
	public void testLogin() throws Exception {

		mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
	}
*/}
