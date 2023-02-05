# eversoul-strategy

eversoul tactic site backend server
- hexagonal architecture
- spring boot multi module project

# Introduce
```
This is the backend server for strategy for the game Eversoul,
including completion of different stages,
its tactics and answers, and information about its soul-character

In order to more easily write the test code,
I applied hexagonal architecture and loosened the dependencies
```

  - spring boot
  - spring data jpa
  - spring batch
  - spring security
  - spring data redis
  - spring restdocs
  - jacoco
  - assertj
  - mokito

---

# project info
  ```
  project-module { 
  
    core: 
    springboot config, component scan
    
    soul-character: 
    soul-caharacter domain business logic & inbound adapter, outboud adapter
    
    stage-tactic: 
    stage-tactic domain business logic & inbound adapter, outboud adapter
    
    story-answer: 
    story-answer domain business logic & inbound adapter, outboud adapter
    
    batch: 
    spring batch on-demand, statistic domain business logic & inbound adapter, outboud adapter
    
    util: 
    enum-model, const-model, event-model
    
  }
  The dependencies between modules were reduced by applying an event-driven model.
  ```


---

## project architecture

![esproject-architecture drawio](https://user-images.githubusercontent.com/95848796/216316351-6f213c6f-2966-4239-8266-f3f6fca9aedc.png)


---


## project ERD
![eversoul-strategy](https://user-images.githubusercontent.com/95848796/216323175-946efe3e-728c-4d90-abb9-849c55e289a1.png)


---

## project deploy
  - project link
    - https://eversoul.pages.dev
  - backend restdocs
    - https://api.eversoul.site/docs
  - back-end
    - framework: spring boot
    - infra: aws ec2
      - nginx
      - docker network
        - spring container
        - redis container
    - database: aws rds
      - mysql
  - front-end
    - framework: svelteKit
    - infra: cloudflare
    - project repository: https://github.com/5witchkr/eversoul

![화면 캡처 2023-02-05 142949](https://user-images.githubusercontent.com/95848796/216806662-acb7f9b2-e292-4dcc-9603-3f805cd04948.png)

---
