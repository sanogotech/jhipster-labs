package com.mygglo.jhipster.subscription.web.rest;

import com.mygglo.jhipster.subscription.SubscriptionApp;

import com.mygglo.jhipster.subscription.domain.MySubscription;
import com.mygglo.jhipster.subscription.repository.MySubscriptionRepository;
import com.mygglo.jhipster.subscription.service.MySubscriptionService;
import com.mygglo.jhipster.subscription.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MySubscriptionResource REST controller.
 *
 * @see MySubscriptionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SubscriptionApp.class)
public class MySubscriptionResourceIntTest {

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PERSON_ID = 1L;
    private static final Long UPDATED_PERSON_ID = 2L;

    @Autowired
    private MySubscriptionRepository mySubscriptionRepository;

    @Autowired
    private MySubscriptionService mySubscriptionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMySubscriptionMockMvc;

    private MySubscription mySubscription;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MySubscriptionResource mySubscriptionResource = new MySubscriptionResource(mySubscriptionService);
        this.restMySubscriptionMockMvc = MockMvcBuilders.standaloneSetup(mySubscriptionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MySubscription createEntity(EntityManager em) {
        MySubscription mySubscription = new MySubscription()
            .label(DEFAULT_LABEL)
            .date(DEFAULT_DATE)
            .personId(DEFAULT_PERSON_ID);
        return mySubscription;
    }

    @Before
    public void initTest() {
        mySubscription = createEntity(em);
    }

    @Test
    @Transactional
    public void createMySubscription() throws Exception {
        int databaseSizeBeforeCreate = mySubscriptionRepository.findAll().size();

        // Create the MySubscription
        restMySubscriptionMockMvc.perform(post("/api/my-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mySubscription)))
            .andExpect(status().isCreated());

        // Validate the MySubscription in the database
        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeCreate + 1);
        MySubscription testMySubscription = mySubscriptionList.get(mySubscriptionList.size() - 1);
        assertThat(testMySubscription.getLabel()).isEqualTo(DEFAULT_LABEL);
        assertThat(testMySubscription.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testMySubscription.getPersonId()).isEqualTo(DEFAULT_PERSON_ID);
    }

    @Test
    @Transactional
    public void createMySubscriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mySubscriptionRepository.findAll().size();

        // Create the MySubscription with an existing ID
        mySubscription.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMySubscriptionMockMvc.perform(post("/api/my-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mySubscription)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLabelIsRequired() throws Exception {
        int databaseSizeBeforeTest = mySubscriptionRepository.findAll().size();
        // set the field null
        mySubscription.setLabel(null);

        // Create the MySubscription, which fails.

        restMySubscriptionMockMvc.perform(post("/api/my-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mySubscription)))
            .andExpect(status().isBadRequest());

        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = mySubscriptionRepository.findAll().size();
        // set the field null
        mySubscription.setDate(null);

        // Create the MySubscription, which fails.

        restMySubscriptionMockMvc.perform(post("/api/my-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mySubscription)))
            .andExpect(status().isBadRequest());

        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMySubscriptions() throws Exception {
        // Initialize the database
        mySubscriptionRepository.saveAndFlush(mySubscription);

        // Get all the mySubscriptionList
        restMySubscriptionMockMvc.perform(get("/api/my-subscriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mySubscription.getId().intValue())))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].personId").value(hasItem(DEFAULT_PERSON_ID.intValue())));
    }

    @Test
    @Transactional
    public void getMySubscription() throws Exception {
        // Initialize the database
        mySubscriptionRepository.saveAndFlush(mySubscription);

        // Get the mySubscription
        restMySubscriptionMockMvc.perform(get("/api/my-subscriptions/{id}", mySubscription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mySubscription.getId().intValue()))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.personId").value(DEFAULT_PERSON_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMySubscription() throws Exception {
        // Get the mySubscription
        restMySubscriptionMockMvc.perform(get("/api/my-subscriptions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMySubscription() throws Exception {
        // Initialize the database
        mySubscriptionService.save(mySubscription);

        int databaseSizeBeforeUpdate = mySubscriptionRepository.findAll().size();

        // Update the mySubscription
        MySubscription updatedMySubscription = mySubscriptionRepository.findOne(mySubscription.getId());
        updatedMySubscription
            .label(UPDATED_LABEL)
            .date(UPDATED_DATE)
            .personId(UPDATED_PERSON_ID);

        restMySubscriptionMockMvc.perform(put("/api/my-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMySubscription)))
            .andExpect(status().isOk());

        // Validate the MySubscription in the database
        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeUpdate);
        MySubscription testMySubscription = mySubscriptionList.get(mySubscriptionList.size() - 1);
        assertThat(testMySubscription.getLabel()).isEqualTo(UPDATED_LABEL);
        assertThat(testMySubscription.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testMySubscription.getPersonId()).isEqualTo(UPDATED_PERSON_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingMySubscription() throws Exception {
        int databaseSizeBeforeUpdate = mySubscriptionRepository.findAll().size();

        // Create the MySubscription

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMySubscriptionMockMvc.perform(put("/api/my-subscriptions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mySubscription)))
            .andExpect(status().isCreated());

        // Validate the MySubscription in the database
        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMySubscription() throws Exception {
        // Initialize the database
        mySubscriptionService.save(mySubscription);

        int databaseSizeBeforeDelete = mySubscriptionRepository.findAll().size();

        // Get the mySubscription
        restMySubscriptionMockMvc.perform(delete("/api/my-subscriptions/{id}", mySubscription.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MySubscription> mySubscriptionList = mySubscriptionRepository.findAll();
        assertThat(mySubscriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MySubscription.class);
        MySubscription mySubscription1 = new MySubscription();
        mySubscription1.setId(1L);
        MySubscription mySubscription2 = new MySubscription();
        mySubscription2.setId(mySubscription1.getId());
        assertThat(mySubscription1).isEqualTo(mySubscription2);
        mySubscription2.setId(2L);
        assertThat(mySubscription1).isNotEqualTo(mySubscription2);
        mySubscription1.setId(null);
        assertThat(mySubscription1).isNotEqualTo(mySubscription2);
    }
}
