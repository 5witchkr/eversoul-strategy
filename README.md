# eversoul-strategy


eversoul tactic site backend server

hexagonal architecture & multi module project

  - spring boot
  - spring data jpa
  - spring batch
  - spring security
  - jacoco
  - asserj

# project info
  ```
  multi module project
  
  project-module { 
    core: springboot 
    soul-character,
    stage-tactic,
    story-answer,
    batch,
    util
  }
  soulcharacter change event pub -> event sub tactic-soul, answer-soul 
  ```


  - back-end : spring boot
  - front-end : svelte

## project architecture

![esproject-architecture drawio](https://user-images.githubusercontent.com/95848796/216316351-6f213c6f-2966-4239-8266-f3f6fca9aedc.png)


## project ERD
![eversoul-strategy](https://user-images.githubusercontent.com/95848796/216323175-946efe3e-728c-4d90-abb9-849c55e289a1.png)



## project deploy
  - project link
    - https://eversoul.pages.dev
  - back-end
    - framework: spring boot
    - infra: aws ec2
    - database: mysql aws rds
  - front-end
    - framework: svelte
    - infra: cloudflare

