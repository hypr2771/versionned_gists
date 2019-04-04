package net.vince.gists.controllers;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.vince.gists.components.Greeter;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class LoadJarController {

  private final ApplicationContext context;

  @GetMapping("/mount/${jar}")
  public ResponseEntity<Optional> mount(@PathVariable String jar){
    AutowireCapableBeanFactory beanFactory=context.getAutowireCapableBeanFactory();
    beanFactory.autowire(Greeter.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);

    return ResponseEntity.of(Optional.empty());
  }
}
