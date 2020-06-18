# jscrappy
### A framework to simple schedule scraping task on java+spring

## Use
```
@SpringBootApplication
@Import(ScrapConfig.class)
public class SimpleScrapApplication {
  ....
}
```

```
@ScrapperTask(value="https://blog.scrapinghub.com", interval = 60)
public class ScrapperScrapinghub implements Scrapper<String>{

  @Override
  public Element parse(Document document) {
    //TODO
  }
  
  @Override
  public String convert(String object) {
    return object;
  }

  @Override
  public void validate(String object) {
    // TODO

  }

  @Override
  public void save(String object) {
    //TODO
  }
}
```
