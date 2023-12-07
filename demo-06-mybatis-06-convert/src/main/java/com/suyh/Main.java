/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package com.suyh;

import com.suyh.convert.SourceTargetMapper;
import com.suyh.vo.Source;
import com.suyh.vo.Target;

public class Main {

    public static void main( String[] args ) {
        Source s = new Source();
        s.setTest( "5" );

        Target t = SourceTargetMapper.MAPPER.toTarget( s );
        System.out.println( t.getTesting() );
    }
}
