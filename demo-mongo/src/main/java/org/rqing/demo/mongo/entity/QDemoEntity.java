package org.rqing.demo.mongo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDemoEntity is a Querydsl query type for DemoEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDemoEntity extends EntityPathBase<DemoEntity> {

    private static final long serialVersionUID = 891042113L;

    public static final QDemoEntity DEMO_ENTITY = new QDemoEntity("demoEntity");

    public final StringPath id = createString("id");

    public final SimplePath<DemoEntity.InnerEntity> inner = createSimple("inner", DemoEntity.InnerEntity.class);

    public final ListPath<Integer, NumberPath<Integer>> list = this.<Integer, NumberPath<Integer>>createList("list", Integer.class, NumberPath.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public QDemoEntity(String variable) {
        super(DemoEntity.class, forVariable(variable));
    }

    public QDemoEntity(Path<? extends DemoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDemoEntity(PathMetadata metadata) {
        super(DemoEntity.class, metadata);
    }

}

